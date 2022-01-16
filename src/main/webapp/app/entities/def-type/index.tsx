import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DefType from './def-type';
import DefTypeDetail from './def-type-detail';
import DefTypeUpdate from './def-type-update';
import DefTypeDeleteDialog from './def-type-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DefTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DefTypeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DefTypeDetail} />
      <ErrorBoundaryRoute path={match.url} component={DefType} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DefTypeDeleteDialog} />
  </>
);

export default Routes;
