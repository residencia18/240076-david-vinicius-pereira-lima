import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { config } from '../app.config';

@Injectable({
  providedIn: 'root',
})
export class DataBaseService {
  private readonly firebaseURL = config.firebaseURL;

  constructor(private http: HttpClient) {}

  post(objeto: any, tabela: string = objeto.constructor.name) {
    console.log(objeto);
    return this.http.post(`${this.firebaseURL}${tabela}.json`, objeto);
  }

  getAll(tabela: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.firebaseURL}${tabela}.json`);
  }

  get(key: string, tabela: string) {
    return this.http.get(`${this.firebaseURL}${tabela}/${key}.json`);
  }

  getByColumn(key: string, tabela: string, column: string): Observable<any> {
    return this.http.get<any>(`${this.firebaseURL}${tabela}.json?orderBy="${column}"&equalTo=${key}`);
  }

  put(key: string, objeto: any, tabela: string = objeto.constructor.name) {
    return this.http.put(`${this.firebaseURL}${tabela}/${key}.json`, objeto);
  }

  delete(key: string, tabela: string) {
    return this.http.delete(`${this.firebaseURL}${tabela}/${key}.json`);
  }
}
