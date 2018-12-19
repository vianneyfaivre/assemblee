import * as React from 'react';
import {BrowserRouter, NavLink, Route} from 'react-router-dom';
import PersonSearchPage from 'src/pages/PersonSearchPage';
import PersonDetailsPage from 'src/pages/PersonDetailsPage';
import PoliticalBodyDetailsPage from 'src/pages/PoliticalBodyDetailsPage';

export default class App extends React.Component {

  public render() {
    return (
      <BrowserRouter>
        <div>

          <nav className="navbar has-background-light" role="navigation" aria-label="main navigation">

            <div className="navbar-brand">
              <NavLink to="/" className="navbar-item is-size-4">🏛️ Mon Assemblée</NavLink>
              <NavLink to="/recherche-depute" className="navbar-item">Rechercher un député</NavLink>
            </div>
          </nav>

          <Route path="/recherche-depute" exact={true} component={PersonSearchPage} />
          <Route path="/deputes/:id" exact={true} component={PersonDetailsPage} />
          <Route path="/organes/:id" exact={true} component={PoliticalBodyDetailsPage} />
          <Route path="/" exact={true} component={PersonSearchPage} />

        </div>
      </BrowserRouter>
    );
  }
}
