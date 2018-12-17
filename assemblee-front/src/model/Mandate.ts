export default class Mandate {
    
    public mandateId: string;
    public startDate: Date;
    public endDate?: Date;
    public politicalBodyId: string;
    public politicalBodyType: string; // TODO use enum
    public politicalBodyLabel: string; 
}