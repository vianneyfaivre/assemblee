import * as React from 'react';
import Api from 'src/api/api';
import PersonSearchItem from 'src/model/PersonSearchItem';
import { Link, withRouter, RouteComponentProps } from 'react-router-dom';
/* tslint:disable-next-line:no-var-requires 
this module does not have the Typescript typings file so we must use "require" */
const Autocomplete = require("react-autocomplete") as any;

interface IPersonSearchState {
    persons: PersonSearchItem[],
    term: string
}

class PersonSearchPage extends React.Component<RouteComponentProps, IPersonSearchState> {

    constructor(props: any){
        super(props);
        this.state = { 
            persons: [],
            term: ''
        };
    }

    public render() {
        return (
            <section className="section">
                <div className="container">
                    <h2 className="title">Rechercher un député</h2>

                    <div className="field">
                        <label htmlFor="person-search-input" className="label">Par nom de famille : </label>
                        
                        <Autocomplete
                            wrapperProps={{className: "control"}}
                            wrapperStyle={{ position: 'relative', display: 'inline-block' }}
                            inputProps={{ name: 'person-search-input', className: "input" }}
                            value={this.state.term}
                            items={this.state.persons}
                            onChange={this.onInputChange}
                            onSelect={this.onItemSelect}
                            getItemValue={this.renderAutocompleteItemValue}
                            renderMenu={this.renderAutocompleteItems}
                            renderItem={this.renderAutocompleteItem}
                        />
                    </div>
                </div>
            </section>
        )
    }

    /**
     * Search for a person when something gets typed in the search box
     */
    private onInputChange = (event: Event, value: string) => {
        this.setState({
            term: value
        });

        if(value.length > 1) {
            Api.searchPersonByLastName(value)
                .then(response => {
                    this.setState({
                        persons: response.data,
                    });
                })
                .catch(error => {
                    // TODO handle error
                    alert(error);
                });
        } else {
            this.setState({
                persons: [],
            });
        }
    }

    /** 
     * Also redirect the user when he selects an item with the Enter key stroke 
     */
    private onItemSelect = (event: Event, value: PersonSearchItem) => {
        this.props.history.push('/depute/'+value.id);
    }

    /**
     * String value that will be passed as "value" parameter in method #onInputSelect
     */
    private renderAutocompleteItemValue(item: PersonSearchItem): string {
        return item.id;
    }

    /**
     * DOM Wrapper element for all autocomplete items
     */
    private renderAutocompleteItems(children: any[]) {

        if(children.length === 0) {
            return <span />;
        }

        return (
            <div className="box">
                {children}
            </div>
        );
    }

    /**
     * Dom renderer for each autocomplete item
     */
    private renderAutocompleteItem(item: PersonSearchItem, isHighlighted: boolean) {
        return (
            <div
                className={`${isHighlighted ? 'has-background-light' : ''}`}
                key={item.id}
            >
                <Link to={'/depute/'+item.id}>
                    {item.lastName} {item.firstName}
                </Link> 
            </div>
        );
    }
}

export default withRouter(PersonSearchPage);