import Mandate from './Mandate';
import MandateGrouped from './MandateGrouped';

export default class PersonMandates {

    public mainMandate: Mandate;
    public politicalMandates: MandateGrouped[];
    public governmentMandates: MandateGrouped[];
    public otherMandates: MandateGrouped[];
}