export class Sessao{
    constructor(
        public id : string,
        public data : string,
        public descricao : string,
        public brincos : string[],
        public tarefas : Tarefa[]
    ){}
}

export class Tarefa{
    constructor(
        public brinco : string,
        public descricao : string,
        public status : boolean
    ){}
}