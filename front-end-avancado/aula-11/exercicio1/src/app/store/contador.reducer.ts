import { createReducer, on } from "@ngrx/store";
import { incrementar } from "./contador.actions";

const estadoInicial = 0;
export const contadorReducer = createReducer(
    estadoInicial,
    on(incrementar, (estado, action) => estado + action.valor),
    on(incrementar, (estado, action) => estado - action.valor)
);