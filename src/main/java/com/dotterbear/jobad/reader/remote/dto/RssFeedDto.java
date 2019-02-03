package com.dotterbear.jobad.reader.remote.dto;

import java.util.Date;
import java.util.List;

public class RssFeedDto {

	private String id;

	private Date createDate;

	private String lastUpdateDate;

	private Channel channel;

	@Override
	public String toString() {
		return "RssFeedDto [id=" + id + ", createDate=" + createDate + ", lastUpdateDate=" + lastUpdateDate
				+ ", channel=" + channel + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public class Channel {
		private String pubDate;

		private String title;

		private String category;

		private String description;

		private String lastBuildDate;

		private List<JobAdRssDto> item;

		@Override
		public String toString() {
			return "Channel [pubDate=" + pubDate + ", title=" + title + ", category=" + category + ", description="
					+ description + ", lastBuildDate=" + lastBuildDate + ", item=" + item + "]";
		}

		public String getPubDate() {
			return pubDate;
		}

		public void setPubDate(String pubDate) {
			this.pubDate = pubDate;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getLastBuildDate() {
			return lastBuildDate;
		}

		public void setLastBuildDate(String lastBuildDate) {
			this.lastBuildDate = lastBuildDate;
		}

		public List<JobAdRssDto> getItem() {
			return item;
		}

		public void setItem(List<JobAdRssDto> item) {
			this.item = item;
		}

		public class JobAdRssDto {
			private String guid;

			private String pubDate;

			private String title;

			private String description;

			private String link;

			public String getGuid() {
				return guid;
			}

			public void setGuid(String guid) {
				this.guid = guid;
			}

			public String getPubDate() {
				return pubDate;
			}

			public void setPubDate(String pubDate) {
				this.pubDate = pubDate;
			}

			public String getTitle() {
				return title;
			}

			public void setTitle(String title) {
				this.title = title;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getLink() {
				return link;
			}

			public void setLink(String link) {
				this.link = link;
			}

			@Override
			public String toString() {
				return "JobAdRssDto [guid=" + guid + ", pubDate=" + pubDate + ", title=" + title + ", description="
						+ description + ", link=" + link + "]";
			}
		}

	}

}
