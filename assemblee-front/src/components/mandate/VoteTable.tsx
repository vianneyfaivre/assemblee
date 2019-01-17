import * as React from 'react';
import PersonVote from 'src/model/PersonVote';
import PersonSearchItem from 'src/model/PersonSearchItem';
import { Link } from 'react-router-dom';

interface IVoteTableProps {
    votes: PersonVote[],
    selectedPerson: PersonSearchItem
}

export const VoteTable : React.StatelessComponent<IVoteTableProps> = (props) => {
    
    const rows = props.votes.map((vote) => {
        return (
            <tr key={vote.scrutinId}>
                <td>
                    <Link to={'/scrutins/'+vote.scrutinId}>
                        {vote.title}
                    </Link>
                </td>
                <td>{vote.applicant}</td>
                <td>Séance link:n°{vote.meetingId} de la session link:n°{vote.sessionId} du {vote.voteDate.toLocaleDateString('fr-FR')}</td>
                <td>{vote.result}</td>
                <td>{vote.choice} {vote.choiceCause}</td>
                <td>
                    <Link to={'/organes/'+vote.politicalBodyId}>
                        {vote.politicalBodyName}
                    </Link>
                </td>
            </tr>
        );
    });

    return (
        <div style={{marginTop: '50px'}}>
            <h3 className="subtitle">Ses votes publics : </h3>

            <table className="table is-bordered is-narrow is-hoverable is-striped">

                <thead>
                    <tr>
                        <th>Scrutin</th>
                        <th>Demandeur</th>
                        <th>Date du scrutin</th>
                        <th>Résultat du scrutin</th>
                        <th>Vote de {props.selectedPerson.firstName} {props.selectedPerson.lastName}</th>
                        <th>Appartenance au groupe de l'assemblée nationale</th>
                    </tr>
                </thead>

                <tbody>
                    {rows}
                </tbody>

            </table>
        </div>
    );
}