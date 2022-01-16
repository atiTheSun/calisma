import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DefType from './def-type';
import DefItem from './def-item';
import DefKitap from './def-kitap';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}def-type`} component={DefType} />
      <ErrorBoundaryRoute path={`${match.url}def-item`} component={DefItem} />
      <ErrorBoundaryRoute path={`${match.url}def-kitap`} component={DefKitap} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
