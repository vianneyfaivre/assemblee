import Mandate from './Mandate';

export default class MandateGrouped {
    
    public politicalBodyId: string;
    public politicalBodyLabel: string; 
    public legislature: number;
    public mandates: Mandate[];
}