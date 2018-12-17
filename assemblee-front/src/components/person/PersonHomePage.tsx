import * as React from 'react';

import PersonSearch from 'src/components/person/PersonSearch';

export default class PersonHomePage extends React.Component {

    public render() {
        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">
                        Rechercher mon député
                    </h2>

                    <PersonSearch />
                </div>
            </section>
        )
    }
}