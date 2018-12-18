import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';
import { MandateTable } from '../mandate/MandateTable';
import { MainMandate } from '../mandate/MainMandate';

interface ISelectedPersonProps {
    selectedPerson?: PersonSearchItem,
    mandates?: PersonMandates,
}

export const SelectedPerson: React.StatelessComponent<ISelectedPersonProps> = (props) => {

    if(props.selectedPerson && props.mandates) {

        const genderLabel = props.selectedPerson.gender === "FEMALE" ? 'Ma députée' : 'Mon député';

        return (
            <div>
                <h2 className="title">{genderLabel} : {props.selectedPerson.lastName} {props.selectedPerson.firstName}</h2>

                <div>
                    <MainMandate mandate={props.mandates.mainMandate} />

                    <MandateTable title='Ses participations au gouvernement' mandates={props.mandates.governmentMandates} />

                    <MandateTable title='Ses appartenances à des partis/groupes politiques' mandates={props.mandates.politicalMandates} />

                    <MandateTable title='Ses autres mandats' mandates={props.mandates.otherMandates} />
                </div>
            </div>
        );
    }

    return <div />;
}