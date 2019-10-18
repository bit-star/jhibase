import { Moment } from 'moment';

export interface IHistorySearch {
  id?: number;
  searchConetnt?: string;
  searchCount?: number;
  isHot?: boolean;
  createdDate?: Moment;
  updateDate?: Moment;
}

export class HistorySearch implements IHistorySearch {
  constructor(
    public id?: number,
    public searchConetnt?: string,
    public searchCount?: number,
    public isHot?: boolean,
    public createdDate?: Moment,
    public updateDate?: Moment
  ) {
    this.isHot = this.isHot || false;
  }
}
