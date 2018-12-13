import * as React from 'react';

import PeopleSearch from './PeopleSearch';

export default class AllPeople extends React.Component {

    public render() {
        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">
                        Veuillez sélectionner un député
                    </h2>

                    <PeopleSearch />
                </div>
            </section>
        )
    }
}