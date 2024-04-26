import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'calcularIdadeMeses'
})
export class CalcularIdadeMesesPipe implements PipeTransform {

  transform(dataNascimeto: string): string {
    const dataNascimento = new Date(dataNascimeto.split('/').reverse().join('-'));
    const dataAtual = new Date();    
    const diferencaMeses = (dataAtual.getFullYear() - dataNascimento.getFullYear()) * 12 + (dataAtual.getMonth() - dataNascimento.getMonth());
    console.log('Diferença em meses: ', diferencaMeses);
    const idadeCalculadaMeses = diferencaMeses > 0 ? diferencaMeses : 0;
    return idadeCalculadaMeses + ' meses';
  }

}
