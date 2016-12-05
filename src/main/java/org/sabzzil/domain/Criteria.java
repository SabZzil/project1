package org.sabzzil.domain;

public class Criteria {

	private int articlePerPage;
	private int page;
	private int totalArticles;
	private int replyPerPage;
	private int replyPage;
	private int totalReplies;
	
	public Criteria() {
		this.page = 1;
		this.articlePerPage = 10;
		this.replyPage = 1;
		this.replyPerPage = 10;
	}
	
	public int getReplyPerPage() {
		return replyPerPage;
	}
	
	public void setReplyPerPage (int replyPerPage) {
		this.replyPerPage = replyPerPage;
	}
	
	public int getReplyPage() {
		return replyPage;
	}
	
	public void setReplyPage (int replyPage) {
		this.replyPage = replyPage;
	}

	public int getTotalRelies() {
		return totalReplies;
	}
	
	public void setTotalReplies (int totalReplies) {
		this.totalReplies = totalReplies;
	}
	
	public int getMaxReplyPage() {
		double p = (double)totalReplies/replyPerPage;
		return (int)Math.ceil(p);
	}
	
	public int getReplyPageStart() {
		return Math.max(1,  replyPage-4);
	}
	
	public int getReplyStart() {
		return (replyPage - 1) * replyPerPage;
	}
	
	
	public int getMaxPage() {
		double p = (double)totalArticles/articlePerPage;
		return (int)Math.ceil(p);
	}
	
	public void setTotalArticles(int totalArticles) {
		this.totalArticles = totalArticles;
	}
	
	public int getTotalArticles() {
		return totalArticles;
	}
	
	public int getPageStart() {
		return Math.max(1, page-4);
	}
	
	public int getStart() {
		return (page - 1) * articlePerPage;
	}
	
	public int getArticlePerPage() {
		return articlePerPage;
	}
	
	public void setArticlePerPage(int articlePerPage) {
		this.articlePerPage = articlePerPage; 
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage (int page) {
		this.page = page;
	}
	
}
