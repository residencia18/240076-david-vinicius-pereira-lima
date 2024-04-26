import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { CalcularIdadeMesesPipe } from './pipes/calcular-idade-meses.pipe';
import { PIG_COLLECTION, SESSAO_COLLECTION, WEIGHT_COLLECTION } from './constants';
import { MenubarModule } from 'primeng/menubar';

export { PIG_COLLECTION, SESSAO_COLLECTION, WEIGHT_COLLECTION };

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    CalcularIdadeMesesPipe,
  ],
  imports: [
    CommonModule,
    MenubarModule
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
    CalcularIdadeMesesPipe,
  ]
})
export class SharedModule { }
