import axios, { AxiosPromise, AxiosTransformer } from 'axios';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';
import PoliticalBodyDetails from 'src/model/PoliticalBodyDetails';
import PoliticalBodyMember from 'src/model/PoliticalBodyMember';

export default class Api {

    /**
     * Returns a promise that contains a list of PersonSearchItem
     */
    public static searchPersonByLastName(lastName: string): AxiosPromise<PersonSearchItem[]> {
        return axios.request<PersonSearchItem[]>(
            {
                method: 'get',
                params: {
                    "lastName": lastName
                },
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/persons/search',
            });
    }

    /**
     * Returns a promise that contains a PersonSearchItem
     */
    public static getPersonById(personId: string): AxiosPromise<PersonSearchItem> {
        return axios.request<PersonSearchItem>(
            {
                method: 'get',
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/persons/' + personId,
            });
    }

    /**
     * Returns a promise that contains the mandates a person is assigned to
     */
    public static getPersonMandates(personId: string): AxiosPromise<PersonMandates> {

        let responseTransformers: AxiosTransformer[] = [];
        if(axios.defaults.transformResponse){
            responseTransformers = responseTransformers.concat(axios.defaults.transformResponse);
        }

        // Axios transformer that will be called after the response has been received
        responseTransformers.push((data, headers) => {

            Api.convertMandatToDate(data.mainMandate);

            data.politicalMandates.forEach((mandate: any) => {
                Api.convertMandatToDate(mandate);
            });

            data.governmentMandates.forEach((mandate: any) => {
                Api.convertMandatToDate(mandate);
            });

            data.otherMandates.forEach((mandate: any) => {
                Api.convertMandatToDate(mandate);
            });

            return data;
        });

        return axios.request<PersonMandates>(
            {
                method: 'get',
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/persons/'+personId+'/mandates',
                transformResponse:  responseTransformers
            });
    }

    /**
     * Returns a promise that contains the members of a political body
     */
    public static getPoliticalBodyMembers(politicalBodyId: string): AxiosPromise<PoliticalBodyDetails> {

        let responseTransformers: AxiosTransformer[] = [];
        if(axios.defaults.transformResponse){
            responseTransformers = responseTransformers.concat(axios.defaults.transformResponse);
        }

        // Axios transformer that will be called after the response has been received
        responseTransformers.push((data: PoliticalBodyDetails, headers) => {

            data.members.forEach((politicalBodyMember: PoliticalBodyMember) => {

                politicalBodyMember.mandates.forEach((mandate: any) => {
                    Api.convertMandatToDate(mandate);
                });
            });

            return data;
        });

        return axios.request<PoliticalBodyDetails>(
            {
                method: 'get',
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/political-bodies/' + politicalBodyId + '/members',
                transformResponse:  responseTransformers
            });
    }

    /**
     *  Function that convert ISO dates (string) to Javascript Date for Mandate type
     */
    private static convertMandatToDate(mandate: any) {
        mandate.startDate = new Date(Date.parse(mandate.startDate));
        if(mandate.endDate) {
            mandate.endDate = new Date(Date.parse(mandate.endDate));
        }
    };
}