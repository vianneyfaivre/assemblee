import * as React from 'react';
import {Link} from 'react-router-dom';
import PoliticalBodyMember from 'src/model/PoliticalBodyMember';
import { DatePeriodLabel } from 'src/components/mandate/DatePeriodLabel';

interface IPoliticalBodyMemberTableProps {
    members: PoliticalBodyMember[],
}

export const PoliticalBodyMemberTable : React.StatelessComponent<IPoliticalBodyMemberTableProps> = (props) => {
    
    if(props.members.length === 0) {
        return <div />;
    }
    
    const rows = props.members.map((member) => {

        const genderPrefix: string = member.gender === 'FEMALE' ? 'Mme.' : 'Mr.';
        
        const mandates = member.mandates.map((mandate) => {
            const qualitySuffix= mandate.quality && (mandate.quality.endsWith('du') || mandate.quality.endsWith('au')) ? 'groupe' : '';

            return (
                <div key={mandate.mandateId}>
                    {mandate.quality} {qualitySuffix} <DatePeriodLabel startDate={mandate.startDate} endDate={mandate.endDate} />
                </div>
            );
        });

        return (
            <tr key={member.personId}>
                <td>
                    <Link to={'/deputes/'+member.personId}>
                        {genderPrefix} {member.lastName} {member.firstName} 
                    </Link>
                </td>
                <td>
                    {mandates}
                </td>
            </tr>
        )
     }
    );

    return (
        <div>
            <h3 className="subtitle">Liste des membres :</h3>

            <table className="table is-bordered is-narrow is-hoverable is-striped">

                <thead>
                    <tr>
                        <th>Nom et prénom</th>
                        <th>Rôles</th>
                    </tr>
                </thead>

                <tbody>
                    {rows}
                </tbody>

            </table>
        </div>
    );
}