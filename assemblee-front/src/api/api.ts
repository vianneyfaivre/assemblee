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

        // Function that convert ISO dates (string) to Javascript Date for Mandate type
        const convertMandatToDate = (mandate: any) => {
            mandate.startDate = new Date(Date.parse(mandate.startDate));
            if(mandate.endDate) {
                mandate.endDate = new Date(Date.parse(mandate.endDate));
            }
        };

        // Axios transformer that will be called after the response has been received
        responseTransformers.push((data, headers) => {

            convertMandatToDate(data.mainMandate);

            data.politicalMandates.forEach((mandate: any) => {
                convertMandatToDate(mandate);
            });

            data.governmentMandates.forEach((mandate: any) => {
                convertMandatToDate(mandate);
            });

            data.otherMandates.forEach((mandate: any) => {
                convertMandatToDate(mandate);
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