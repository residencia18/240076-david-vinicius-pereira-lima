import { Routes } from '@angular/router';
import path from 'node:path';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { ProductAddComponent } from './components/product-add/product-add.component';
import { ProductEditComponent } from './components/product-edit/product-edit.component';

export const routes: Routes = [
    {path: '', component : HomeComponent},
    {path: 'login', component : LoginComponent},
    {path: 'register', component : RegisterComponent},
    {path: 'products', component : ProductListComponent},
    {path: 'product/:id', component : ProductDetailComponent},
    {path: 'product-add', component : ProductAddComponent},
    {path: 'product-edit/:id', component : ProductEditComponent},
];
