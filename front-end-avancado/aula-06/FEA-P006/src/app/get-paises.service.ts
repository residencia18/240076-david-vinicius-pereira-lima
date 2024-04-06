import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetPaisesService {

  constructor(private http: HttpClient) { }

  getFirstPais() : Observable<any>{
    return this.http.get<any>("https://restcountries.com/v3.1/all").pipe(
      map(resp => {
        const pais = resp[0];
        const camposForm = Object.keys(pais).map(key => ({
          tipo: 'text',
          nome: key,
          rotulo: key
        }));
        return camposForm;
      }))
  }
}


