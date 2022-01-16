import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './def-kitap.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const DefKitapDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const defKitapEntity = useAppSelector(state => state.defKitap.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="defKitapDetailsHeading">
          <Translate contentKey="startpointApp.defKitap.detail.title">DefKitap</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{defKitapEntity.id}</dd>
          <dt>
            <span id="isbn">
              <Translate contentKey="startpointApp.defKitap.isbn">Isbn</Translate>
            </span>
          </dt>
          <dd>{defKitapEntity.isbn}</dd>
          <dt>
            <span id="kitapAdi">
              <Translate contentKey="startpointApp.defKitap.kitapAdi">Kitap Adi</Translate>
            </span>
          </dt>
          <dd>{defKitapEntity.kitapAdi}</dd>
        </dl>
        <Button tag={Link} to="/def-kitap" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/def-kitap/${defKitapEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DefKitapDetail;
