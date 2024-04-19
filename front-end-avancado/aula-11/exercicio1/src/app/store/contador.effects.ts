import { Injectable } from "@angular/core";
import { Store } from "@ngrx/store";
import { decrementar, incrementar } from "./contador.actions";
import { withLatestFrom, tap } from "rxjs";
import { selectorContador } from "./contador.selectors";
import { Actions, createEffect, ofType } from "@ngrx/effects";
@Injectable()
export class ContadorEffects{
    constructor(private action$:Actions, private store:Store<{contador:number}>){}
    logarAcoes = createEffect(
        ()=>
            this.action$.pipe(
                ofType(incrementar, decrementar),
                withLatestFrom(this.store.select(selectorContador)),
                tap(([action, contador])=>console.log("Effects"+action+"Contador:"+contador))
            ),
            {dispatch:false}
    );
}