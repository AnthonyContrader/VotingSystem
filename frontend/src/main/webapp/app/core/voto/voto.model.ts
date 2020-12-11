export interface IVoto{
    id?: any;
    utente?: any;
    scheda?: any;
    voto?: any;
    
}



export class Voto implements IVoto{
    constructor(
        public id?: any,
        public utente?: any,
        public scheda?: any,
        public voto?: any
    ){}
    
}