import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './def-type.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DefTypeDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const defTypeEntity = useAppSelector(state => state.defType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="defTypeDetailsHeading">
          <Translate contentKey="startpointApp.defType.detail.title">DefType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="startpointApp.defType.id">Id</Translate>
            </span>
          </dt>
          <dd>{defTypeEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="startpointApp.defType.name">Name</Translate>
            </span>
          </dt>
          <dd>{defTypeEntity.name}</dd>
        </dl>
        <Button tag={Link} to="/def-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/def-type/${defTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DefTypeDetail;
