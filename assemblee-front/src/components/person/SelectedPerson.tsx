import * as React from 'react';
import PersonSearchItem from 'src/model/PersonSearchItem';
import PersonMandates from 'src/model/PersonMandates';
import { MandateTable } from 'src/components/mandate/MandateTable';
import { MainMandate } from 'src/components/mandate/MainMandate';
import { ProfilePicture } from 'src/components/person/ProfilePicture';
import PersonVote from 'src/model/PersonVote';
import { VoteTable } from 'src/components/mandate/VoteTable';

interface ISelectedPersonProps {
    selectedPerson: PersonSearchItem,
    mandates?: PersonMandates,
    votes?: PersonVote[]
}

export const SelectedPerson: React.StatelessComponent<ISelectedPersonProps> = (props) => {

    const genderLabel = props.selectedPerson.gender === "FEMALE" ? 'Madame' : 'Monsieur';

    return (
        <div>
            <h2 className="title">{genderLabel} {props.selectedPerson.lastName} {props.selectedPerson.firstName}</h2>

            {(props.mandates && props.votes) &&
                <div>
                    <ProfilePicture person={props.selectedPerson} legislature={props.mandates.mainMandate.legislature} />
    
                    <MainMandate mandate={props.mandates.mainMandate} />

                    <VoteTable votes={props.votes} selectedPerson={props.selectedPerson} />

                    <MandateTable title='Ses participations au gouvernement' mandatesGrouped={props.mandates.governmentMandates} />

                    <MandateTable title="Ses appartenances à des groupes de l'Assemblée Nationale" mandatesGrouped={props.mandates.politicalGroupMandates} />

                    <MandateTable title='Ses appartenances à des partis politiques' mandatesGrouped={props.mandates.politicalPartyMandates} />

                    <MandateTable title='Ses autres mandats' mandatesGrouped={props.mandates.otherMandates} />
                </div>
            }
        </div>
    );
}