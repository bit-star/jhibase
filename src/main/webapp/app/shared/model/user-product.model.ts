import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IUserProduct {
  id?: number;
  productName?: string;
  productDes?: string;
  imgURL?: string;
  createdDate?: Moment;
  updateDate?: Moment;
  user?: IUser;
}

export class UserProduct implements IUserProduct {
  constructor(
    public id?: number,
    public productName?: string,
    public productDes?: string,
    public imgURL?: string,
    public createdDate?: Moment,
    public updateDate?: Moment,
    public user?: IUser
  ) {}
}
