import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale from './locale';
import authentication from './authentication';
import applicationProfile from './application-profile';

import administration from 'app/modules/administration/administration.reducer';
import userManagement from 'app/modules/administration/user-management/user-management.reducer';
import register from 'app/modules/account/register/register.reducer';
import activate from 'app/modules/account/activate/activate.reducer';
import password from 'app/modules/account/password/password.reducer';
import settings from 'app/modules/account/settings/settings.reducer';
import passwordReset from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import defType from 'app/entities/def-type/def-type.reducer';
// prettier-ignore
import defItem from 'app/entities/def-item/def-item.reducer';
// prettier-ignore
import defKitap from 'app/entities/def-kitap/def-kitap.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  defType,
  defItem,
  defKitap,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
