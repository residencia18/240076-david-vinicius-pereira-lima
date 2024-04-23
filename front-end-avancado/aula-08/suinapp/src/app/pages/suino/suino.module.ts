import { NgModule } from '@angular/core';
import { CadastroSuinoComponent } from './cadastro-suino/cadastro-suino.component';
import { EditarSuinosComponent } from './editar-suinos/editar-suinos.component';
import { ListarSuinosComponent } from './listar-suinos/listar-suinos.component';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MessagesModule } from 'primeng/messages';
import { PanelModule } from 'primeng/panel';
import { CalendarModule } from 'primeng/calendar';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { MultiSelectModule } from 'primeng/multiselect';
import { SharedModule } from '../../shared/shared.module';
import { RouterModule, Routes } from '@angular/router';
import { HistoricoAnimalComponent } from './historico-animal/historico-animal.component';

const routes : Routes = [
  { path: 'cadastro-suino', component: CadastroSuinoComponent },
  { path: 'editar-suino/:id', component: EditarSuinosComponent },
  { path: 'listar-suinos', component: ListarSuinosComponent },
  { path: 'historico-animal', component: HistoricoAnimalComponent }
];

@NgModule({
  declarations: [
    CadastroSuinoComponent,
    EditarSuinosComponent,
    ListarSuinosComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule,
    ReactiveFormsModule,
    MessagesModule,
    PanelModule,
    CalendarModule,
    DropdownModule,
    ButtonModule,
    InputTextModule,
    TableModule,
    MultiSelectModule,
    HistoricoAnimalComponent,
    RouterModule.forChild(routes)
  ]
})
export class SuinoModule { }
