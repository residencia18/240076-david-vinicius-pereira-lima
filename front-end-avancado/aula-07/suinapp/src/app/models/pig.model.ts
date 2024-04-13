export class Pig {
    constructor(
        public id: number,
        public father: number,
        public mother: number,
        public birth: string,
        public exit: string,
        public status: string,
        public sex: string
    ) {}
}

export class Weight {
    constructor(
        public id: number | null,
        public pig: number,
        public date: string,
        public weight: number
    ) {}
}