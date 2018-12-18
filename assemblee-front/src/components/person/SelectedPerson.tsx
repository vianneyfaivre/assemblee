import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';
import { MandateTable } from 'src/components/mandate/MandateTable';
import { MainMandate } from 'src/components/mandate/MainMandate';

interface ISelectedPersonProps {
    selectedPerson: PersonSearchItem,
    mandates?: PersonMandates,
}

export const SelectedPerson: React.StatelessComponent<ISelectedPersonProps> = (props) => {

    const genderLabel = props.selectedPerson.gender === "FEMALE" ? 'Ma députée' : 'Mon député';

    return (
        <div>
            <h2 className="title">{genderLabel} : {props.selectedPerson.lastName} {props.selectedPerson.firstName}</h2>

            {props.mandates &&
                <div>
                    <MainMandate mandate={props.mandates.mainMandate} />

                    <MandateTable title='Ses participations au gouvernement' mandates={props.mandates.governmentMandates} />

                    <MandateTable title='Ses appartenances à des partis/groupes politiques' mandates={props.mandates.politicalMandates} />

                    <MandateTable title='Ses autres mandats' mandates={props.mandates.otherMandates} />
                </div>
            }
        </div>
    );
}