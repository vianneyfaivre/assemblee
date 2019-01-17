import * as React from 'react';
import Api from 'src/api/api';
import VoteOverview from 'src/model/VoteOverview';
import { Link } from 'react-router-dom';

interface IScrutinsDetailsPageState {
    scrutinId: string,
    voteOverview?: VoteOverview[],
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
        
        Api.getVoteOverview(this.state.scrutinId)
        .then(response => {
            this.setState({
                voteOverview: response.data,
            });
        })
        .catch(error => {
            // TODO handle error
            alert(error);
        });
    }

    public render() {

        let rows;
        
        if(this.state.voteOverview) {
            rows = this.state.voteOverview.map((voteOverview) => {
                return (
                    <tr key={voteOverview.politicalBodyId}>
                        
                        <td>
                            <Link to={'/organes/'+voteOverview.politicalBodyId}>
                                {voteOverview.politicalBodyName}
                            </Link> 
                        </td>
                        
                        <td>{voteOverview.choice}</td>
                        
                        <td>
                            Pour : {voteOverview.numberFor}<br />
                            Contre : {voteOverview.numberAgainst}<br />
                            Abstention : {voteOverview.numberAbstention}<br />
                            Non-Votant : {voteOverview.numberNoVote}
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