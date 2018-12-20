import axios, { AxiosPromise, AxiosTransformer } from 'axios';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';
import PoliticalBodyDetails from 'src/model/PoliticalBodyDetails';
import PoliticalBodyMember from 'src/model/PoliticalBodyMember';
import MandateGrouped from 'src/model/MandateGrouped';
import PoliticalBodySearchItem from 'src/model/PoliticalBodySearchItem';

export default class Api {

    /**
     * Returns a list of political bodies by searching by a name
     */
    public static searchPoliticalBodyByName(name: string): AxiosPromise<PoliticalBodySearchItem[]> {
        
        let responseTransformers: AxiosTransformer[] = [];
        if(axios.defaults.transformResponse){
            responseTransformers = responseTransformers.concat(axios.defaults.transformResponse);
        }

        // Axios transformer that will be called after the response has been received
        responseTransformers.push((data: PoliticalBodySearchItem[], headers) => {
            data.forEach((politicalBody: any) => {
                
                if(politicalBody.startDate) {
                    politicalBody.startDate = new Date(Date.parse(politicalBody.startDate));
                }

                if(politicalBody.endDate) {
                    politicalBody.endDate = new Date(Date.parse(politicalBody.endDate));
                }
            });

            return data;
        });
        
        return axios.request<PoliticalBodySearchItem[]>(
            {
                method: 'get',
                params: {
                    "name": name
                },
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/political-bodies/search',
                transformResponse:  responseTransformers
            });
    }

    /**
     * Returns a list of persons by searching by a last name 
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
     * Returns a person by its id
     */
    public static getPersonById(personId: string): AxiosPromise<PersonSearchItem> {
        return axios.request<PersonSearchItem>(
            {
                method: 'get',
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/persons/' + personId,
            });
    }

    /**
     * Returns the mandates a person is assigned to
     */
    public static getPersonMandates(personId: string): AxiosPromise<PersonMandates> {

        let responseTransformers: AxiosTransformer[] = [];
        if(axios.defaults.transformResponse){
            responseTransformers = responseTransformers.concat(axios.defaults.transformResponse);
        }

        // Axios transformer that will be called after the response has been received
        responseTransformers.push((data: PersonMandates, headers) => {

            Api.convertDateFields(data.mainMandate);

            data.politicalPartyMandates.forEach((mandatesGrouped: MandateGrouped) => {
                mandatesGrouped.mandates.forEach(mandate => {
                    Api.convertDateFields(mandate);
                });
            });

            data.politicalGroupMandates.forEach((mandatesGrouped: MandateGrouped) => {
                mandatesGrouped.mandates.forEach(mandate => {
                    Api.convertDateFields(mandate);
                });
            });

            data.governmentMandates.forEach((mandatesGrouped: MandateGrouped) => {
                mandatesGrouped.mandates.forEach(mandate => {
                    Api.convertDateFields(mandate);
                });
            });

            data.otherMandates.forEach((mandatesGrouped: MandateGrouped) => {
                mandatesGrouped.mandates.forEach(mandate => {
                    Api.convertDateFields(mandate);
                });
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
     * Returns the members of a political body
     */
    public static getPoliticalBodyMembers(politicalBodyId: string): AxiosPromise<PoliticalBodyDetails> {

        let responseTransformers: AxiosTransformer[] = [];
        if(axios.defaults.transformResponse){
            responseTransformers = responseTransformers.concat(axios.defaults.transformResponse);
        }

        // Axios transformer that will be called after the response has been received
        responseTransformers.push((data, headers) => {

            data.startDate = new Date(Date.parse(data.startDate));

            if(data.endDate) {
                data.endDate = new Date(Date.parse(data.endDate));
            }

            data.members.forEach((politicalBodyMember: PoliticalBodyMember) => {

                politicalBodyMember.mandates.forEach((mandate: any) => {
                    Api.convertDateFields(mandate);
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
     * Function that convert ISO dates (string) to Javascript Date for Mandate type
     */
    private static convertDateFields(mandate: any) {
        mandate.startDate = new Date(Date.parse(mandate.startDate));
        if(mandate.endDate) {
            mandate.endDate = new Date(Date.parse(mandate.endDate));
        }
    };
}