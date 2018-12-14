import axios, { AxiosPromise } from 'axios';
import PersonSearchItem from 'src/Model/PersonSearchItem';

export default class Api {

    /**
     * Returns a promise that contains a list of PersonSearchItem
     */
    public static searchPeopleByLastName(lastName: string): AxiosPromise<PersonSearchItem[]> {
        return axios.request<PersonSearchItem[]>(
            {
                method: 'get',
                params: {
                    "lastName": lastName
                },
                url: process.env.REACT_APP_ASSEMBLEE_BACKEND_URL + '/persons/search',
            });
    }

}