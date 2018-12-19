import * as React from 'react';
import { DatePeriodLabel } from 'src/components/mandate/DatePeriodLabel';
import PoliticalBodyDetails from 'src/model/PoliticalBodyDetails';

interface IPoliticalBodyDetailsPanelProps {
    details: PoliticalBodyDetails,
}

export const PoliticalBodyDetailsPanel : React.StatelessComponent<IPoliticalBodyDetailsPanelProps> = (props) => {

    let labelValidityPrefix = 'Cet organe existe depuis le';
    if(props.details.endDate) {
        labelValidityPrefix = 'Cet organe a existé'
    }

    const totalCount: number = props.details.members.length;
    const femaleCount: number = props.details.members.filter(member => member.gender === "FEMALE").length;
    const maleCount: number = totalCount - femaleCount;

    return (
        <div>
            <p>{labelValidityPrefix} <DatePeriodLabel startDate={props.details.startDate} endDate={props.details.endDate} /></p>

            <p>Composé de {totalCount} membres dont ♀️{femaleCount} femmes et ♂️{maleCount} hommes</p>
        </div>
    );
}