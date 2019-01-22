export default class VoteOverview {

    public title: string;
    public sessionId: string;
    public meetingId: string;
    public voteDate: Date;
    public result: string;
    public applicant?: string;
    public voteType: string;
    public votePublicationMode: string;
    public numberFor: number;
    public numberAgainst: number;
    public numberAbstention: number;
    public numberNoVote: number;
}