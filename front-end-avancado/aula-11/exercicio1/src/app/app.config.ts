import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideEffects } from "@ngrx/effects";
import { provideStore } from '@ngrx/store';
import { contadorReducer } from './store/contador.reducer';
import { ContadorEffects } from './store/contador.effects';

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideClientHydration(), provideStore({contador:contadorReducer}), provideEffects([ContadorEffects])]
};
