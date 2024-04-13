import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpParams
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { exhaustMap, take } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authServico: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return this.authServico.user.pipe(
      take(1),
      exhaustMap(usuario => this.handleRequestWithAuth(request, next, usuario))
    );
  }

  private handleRequestWithAuth(request: HttpRequest<unknown>, next: HttpHandler, usuario: any): Observable<HttpEvent<unknown>> {
    if (!usuario) {
      return next.handle(request);
    }

    const requestModificado = request.clone({
      params: new HttpParams().set('auth', usuario.token!)
    });

    return next.handle(requestModificado);
  }
}