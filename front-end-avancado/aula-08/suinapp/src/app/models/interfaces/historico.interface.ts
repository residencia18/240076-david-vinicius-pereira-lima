export interface HistoricoAnimal {
    brinco: number,
    historico: HistoricoItem[] | undefined
}

export interface HistoricoItem {
    data: string,
    atividade: string,
    resultado: string
}