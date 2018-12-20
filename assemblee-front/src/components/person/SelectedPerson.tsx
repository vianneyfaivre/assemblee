import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';
import { MandateTable } from 'src/components/mandate/MandateTable';
import { MainMandate } from 'src/components/mandate/MainMandate';
import Mandate from 'src/model/Mandate';

interface ISelectedPersonProps {
    selectedPerson: PersonSearchItem,
    mandates?: PersonMandates,
}

export const SelectedPerson: React.StatelessComponent<ISelectedPersonProps> = (props) => {

    const genderLabel = props.selectedPerson.gender === "FEMALE" ? 'Ma députée' : 'Mon député';

    const getProfilePic = (mainMandate: Mandate, selectedPerson: PersonSearchItem) => {
       return `http://www2.assemblee-nationale.fr/static/tribun/${mainMandate.legislature}/photos/${selectedPerson.id.split("PA")[1]}.jpg`;
    };

    return (
        <div>
            <h2 className="title">{genderLabel} : {props.selectedPerson.lastName} {props.selectedPerson.firstName}</h2>

            {props.mandates &&
                <div>
                    <img src={getProfilePic(props.mandates.mainMandate, props.selectedPerson)} alt={props.selectedPerson.lastName + ' ' + props.selectedPerson.firstName} />
    
                    <MainMandate mandate={props.mandates.mainMandate} />

                    <MandateTable title='Ses participations au gouvernement' mandatesGrouped={props.mandates.governmentMandates} />

                    <MandateTable title='Ses appartenances à des partis/groupes politiques' mandatesGrouped={props.mandates.politicalMandates} />

                    <MandateTable title='Ses autres mandats' mandatesGrouped={props.mandates.otherMandates} />
                </div>
            }
        </div>
    );
}