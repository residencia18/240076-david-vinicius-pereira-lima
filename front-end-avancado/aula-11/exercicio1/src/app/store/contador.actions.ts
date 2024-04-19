import { createAction, props } from "@ngrx/store";

export const incrementar = createAction(
    '[(Contador)]Incrementar',
    props<{valor:number}>()
);
export const decrementar =createAction(
    '[(Contador)Decrementar]',
    props<{valor:number}>()
);