import * as React from 'react';
import Mandate from 'src/model/Mandate';

interface IMainMandateProps {
    mandate: Mandate
}

export const MainMandate : React.StatelessComponent<IMainMandateProps> = (props) => {

    const mainMandateEnded: boolean = props.mandate.endDate !== null;

    return (
        <div>
            Mandat principal à l'{props.mandate.politicalBodyLabel}&nbsp;

            {mainMandateEnded && 
                <span>(du {props.mandate.startDate.toLocaleDateString('fr-FR')} au {props.mandate.endDate!.toLocaleDateString('fr-FR')})</span>
            }

            {!mainMandateEnded && 
                <span>(depuis le {props.mandate.startDate.toLocaleDateString('fr-FR')})</span>
            }
        </div>
    );
}