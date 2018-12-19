import * as React from 'react';
import PoliticalBodyDetails from 'src/model/PoliticalBodyDetails';

interface IPoliticalBodyDetailsPanelProps {
    details: PoliticalBodyDetails,
}

export const PoliticalBodyDetailsPanel : React.StatelessComponent<IPoliticalBodyDetailsPanelProps> = (props) => {

    const totalCount: number = props.details.members.length;
    const femaleCount: number = props.details.members.filter(member => member.gender === "FEMALE").length;
    const maleCount: number = totalCount - femaleCount;

    return (
        <div>
            <p>Composé de {totalCount} membres dont ♀️{femaleCount} femmes et ♂️{maleCount} hommes</p>
        </div>
    );
}