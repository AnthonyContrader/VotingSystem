export interface IScheda{
    id?: any;
    titolo?: string;
    domanda?: string;
    primarisposta?: string;
    secondarisposta?: string;
    terzarisposta?: string;
    votato?: boolean;
}



export class Scheda implements IScheda{
    constructor(
        public id?: any,
        public titolo?: string,
        public domanda?: string,
        public primarisposta?: string,
        public secondarisposta?: string,
        public terzarisposta?: string,
        public votato?: boolean,
    ){}
    
}