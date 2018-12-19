import * as React from 'react';
import {Link} from 'react-router-dom';
import PoliticalBodyMember from 'src/model/PoliticalBodyMember';

interface IPoliticalBodyMemberTableProps {
    members: PoliticalBodyMember[],
}

export const PoliticalBodyMemberTable : React.StatelessComponent<IPoliticalBodyMemberTableProps> = (props) => {
    
    if(props.members.length === 0) {
        return <div />;
    }
    
    const rows = props.members.map((member) => {

        const genderPrefix: string = member.gender === 'FEMALE' ? 'Mme.' : 'Mr.';
        const qualitySuffix: string = member.quality.endsWith('du') || member.quality.endsWith('au') ? 'groupe' : '';

        return (
            <tr key={member.mandateId}>
                <td>
                    <Link to={'/deputes/'+member.personId}>
                        {genderPrefix} {member.lastName} {member.firstName} 
                    </Link>
                </td>
                <td>
                    {member.quality} {qualitySuffix}
                </td>
                <td>
                    {member.endDate && <span>du {member.startDate.toLocaleDateString('fr-FR')} au {member.endDate.toLocaleDateString('fr-FR')}</span>}
                    {!member.endDate && <span>depuis le {member.startDate.toLocaleDateString('fr-FR')}</span>}
                </td>
            </tr>
        )
     }
    );

    return (
        <table className="table is-bordered is-narrow is-hoverable is-striped">

            <thead>
                <tr>
                    <th>Nom et prénom</th>
                    <th>Rôle</th>
                    <th>Période</th>
                </tr>
            </thead>

            <tbody>
                {rows}
            </tbody>

        </table>
    );
}