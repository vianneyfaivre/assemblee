import * as React from 'react';
import Api from 'src/api/api';
import VoteDetailsByGroup from 'src/model/VoteDetailsByGroup';
import { Link } from 'react-router-dom';

interface IScrutinsDetailsPageState {
    scrutinId: string,
    voteDetailsByGroup?: VoteDetailsByGroup[],
}

export default class ScrutinsDetailsPage extends React.Component<{}, IScrutinsDetailsPageState> {

    constructor(props: any){
        super(props);

        // pathName == /scrutins/:id (see routing in App.tsx)
        // => parts: 0='' 1=scrutins 2=:id
        const pathNameParts: string[] = location.pathname.split('/');

        this.state = {
            scrutinId: pathNameParts[2]
        }
    }

    public componentDidMount() {
        
        Api.getVoteDetailsByGroup(this.state.scrutinId)
        .then(response => {
            this.setState({
                voteDetailsByGroup: response.data,
            });
        })
        .catch(error => {
            // TODO handle error
            alert(error);
        });
    }

    public render() {

        let rows;
        
        if(this.state.voteDetailsByGroup) {
            rows = this.state.voteDetailsByGroup.map((voteDetailsByGroup) => {
                return (
                    <tr key={voteDetailsByGroup.politicalBodyId}>
                        
                        <td>
                            <Link to={'/organes/'+voteDetailsByGroup.politicalBodyId}>
                                {voteDetailsByGroup.politicalBodyName}
                            </Link> 
                        </td>
                        
                        <td>{voteDetailsByGroup.choice}</td>
                        
                        <td>
                            Pour : {voteDetailsByGroup.numberFor}<br />
                            Contre : {voteDetailsByGroup.numberAgainst}<br />
                            Abstention : {voteDetailsByGroup.numberAbstention}<br />
                            Non-Votant : {voteDetailsByGroup.numberNoVote}
                        </td>
                    </tr>
                );
            });
        }

        return (
            <section className="section">
                <div className="container">

                    <h2 className="title">Scrutin {this.state.scrutinId}</h2>

                    <div>TODO SCRUTIN DETAILS</div>



                    <h3 className="subtitle">Répartition des votes par groupe politique :</h3>

                    <table className="table is-bordered is-narrow is-hoverable is-striped">

                        <thead>
                            <tr>
                                <th>Groupe à l'assemblée</th>
                                <th>Résultat</th>
                                <th>Détails</th>
                            </tr>
                        </thead>

                        <tbody>
                            {rows}
                        </tbody>
                    </table>
                </div>
            </section>
          );
    }
}