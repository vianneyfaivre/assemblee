import axios, { AxiosPromise } from 'axios';
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
        return axios.request<PersonMandates>(
            {
                method: 'get',
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/persons/'+personId+'/mandates',
            });
    }

}