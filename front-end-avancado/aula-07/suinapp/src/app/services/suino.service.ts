import { Injectable } from '@angular/core';
import { DataBaseService } from './database.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SuinoService {

  private readonly tabela = 'suinos';
  constructor(private databaseService : DataBaseService) { }

  adicionarSuino(suino: any): Observable<any> {
    return this.databaseService.post(suino, this.tabela);
  }

  listarSuinos(): Observable<any[]> {
    return this.databaseService.getAll(this.tabela);
  }

  buscarSuino(key: string): Observable<any> {
    return this.databaseService.get(key, this.tabela);
  }

  atualizarSuino(key: string, suino: any): Observable<any> {
    return this.databaseService.put(key, suino, this.tabela);
  }

  excluirSuino(key: string): Observable<any> {
    return this.databaseService.delete(key, this.tabela);
  }
}
