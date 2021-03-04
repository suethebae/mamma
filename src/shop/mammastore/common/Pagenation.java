package shop.mammastore.common;

public class Pagenation {
	private final int ARTICLE_COUNT_PER_PAGE = 10;
	private final int PAGE_COUNT_PER_GROUP = 5;

	private int nowPageNumber;

	private int totalArticleCount;

	private int startArticleNumber;
	private int endArticleNumber;

	private int totalPageCount;

	private int startPageNumber;
	private int endPageNumber;

	public Pagenation(int nowPageNumber, int totalArticleCount) {
		this.nowPageNumber = nowPageNumber;
		this.totalArticleCount = totalArticleCount;
		/*
		 * this.startArticleNumber = (nowPageNumber * ARTICLE_COUNT_PER_PAGE) -
		 * (ARTICLE_COUNT_PER_PAGE - 1);
		 */
		this.startArticleNumber=(nowPageNumber-1)*ARTICLE_COUNT_PER_PAGE;
		// 시작 글 번호 = (현재 페이지번호 -1) * 한페이지에 보여줄 글 수
		this.endArticleNumber = this.startArticleNumber + ARTICLE_COUNT_PER_PAGE - 1;
		this.totalPageCount = (int) Math.ceil((double) totalArticleCount / ARTICLE_COUNT_PER_PAGE);// 페이지 갯수, 109개 글이
																									// 있을시 페이지 수는 11
		if (this.totalPageCount < 1) {
			this.totalPageCount = 1;
		}

		this.startPageNumber = ((int) (((double) nowPageNumber / PAGE_COUNT_PER_GROUP + 0.9) - 1))
				* PAGE_COUNT_PER_GROUP + 1;

		this.endPageNumber = this.startPageNumber + (PAGE_COUNT_PER_GROUP - 1);
		if (endPageNumber > this.totalPageCount) {
			this.endPageNumber = this.totalPageCount;
		}
	}

	public int getNowPageNumber() {
		return nowPageNumber;
	}

	public void setNowPageNumber(int nowPageNumber) {
		this.nowPageNumber = nowPageNumber;
	}

	public int getTotalArticleCount() {
		return totalArticleCount;
	}

	public void setTotalArticleCount(int totalArticleCount) {
		this.totalArticleCount = totalArticleCount;
	}

	public int getStartArticleNumber() {
		return startArticleNumber;
	}

	public void setStartArticleNumber(int startArticleNumber) {
		this.startArticleNumber = startArticleNumber;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartPageNumber() {
		return startPageNumber;
	}

	public void setStartPageNumber(int startPageNumber) {
		this.startPageNumber = startPageNumber;
	}

	public int getEndPageNumber() {
		return endPageNumber;
	}

	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}

	public int getARTICLE_COUNT_PER_PAGE() {
		return ARTICLE_COUNT_PER_PAGE;
	}

	public int getPAGE_COUNT_PER_GROUP() {
		return PAGE_COUNT_PER_GROUP;
	}

	public int getEndArticleNumber() {
		return endArticleNumber;
	}

	public void setEndArticleNumber(int endArticleNumber) {
		this.endArticleNumber = endArticleNumber;
	}
	
	

}
