export default class PoliticalBodyMember {

    public personId: string;
    public gender: string;
    public firstName: string;
    public lastName: string;
    public mandateId: string;
    public startDate: Date;
    public endDate?: Date;
    public politicalBodyId: string;
    public politicalBodyType: string;
    public politicalBodyLabel: string;
    public legislature: number;
    public cause?: string;
    public quality: string;

}