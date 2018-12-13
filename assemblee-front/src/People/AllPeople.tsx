import * as React from 'react';

import PeopleSearch from './PeopleSearch';

export default class AllPeople extends React.Component {

    public render() {
        return (
            <div>
                <h1>Députés</h1>
                <PeopleSearch />
            </div>
        )
    }
}