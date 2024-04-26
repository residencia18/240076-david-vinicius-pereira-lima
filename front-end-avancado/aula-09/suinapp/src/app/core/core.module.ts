import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthInterceptor } from './auth.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthService } from './services/auth.service';
import { DataBaseService } from './services/database.service';
import { AuthGuard } from './auth.guard';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [ 
    AuthService,
    DataBaseService,
    AuthGuard,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}]
})
export class CoreModule { }
