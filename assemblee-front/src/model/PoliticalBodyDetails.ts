import PoliticalBodyMember from './PoliticalBodyMember';

export default class PoliticalBodyDetails {

    public startDate: Date;
    public endDate: Date;
    public politicalBodyLabel: string;
    public legislature: number;
    public members: PoliticalBodyMember[];
}