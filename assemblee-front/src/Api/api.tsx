import axios, { AxiosPromise, AxiosTransformer } from 'axios';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';

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
     * Returns a promise that contains the mandates a person is assigned to
     */
    public static getPersonMandates(personId: string): AxiosPromise<PersonMandates> {

        let responseTransformers: AxiosTransformer[] = [];
        if(axios.defaults.transformResponse){
            responseTransformers = responseTransformers.concat(axios.defaults.transformResponse);
        }

        // Convert ISO dates (string) to Javascript Date
        responseTransformers.push((data, headers) => {

            data.mainMandate.startDate = new Date(Date.parse(data.mainMandate.startDate));
            data.mainMandate.endDate = new Date(Date.parse(data.mainMandate.endDate));

            data.otherMandates.forEach((mandate: any) => {
                mandate.startDate = new Date(Date.parse(mandate.startDate));
                mandate.endDate = new Date(Date.parse(mandate.endDate));
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

}